/*
 * Copyright 2008-2011 UnboundID Corp. All Rights Reserved.
 */
/*
 * Copyright (C) 2008-2011 UnboundID Corp. This program is free
 * software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License (GPLv2 only) or the terms of the GNU
 * Lesser General Public License (LGPLv2.1 only) as published by the
 * Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details. You
 * should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses>.
 */

package samplecode.bind;

import com.unboundid.ldap.sdk.*;
import com.unboundid.ldap.sdk.controls.*;
import com.unboundid.util.ssl.*;
import java.security.GeneralSecurityException;
import javax.net.SocketFactory;
import samplecode.annotation.*;


/**
 * Provides a method that changes the authentication state of an existing
 * connection to a server. Assumes there is a directory server running on a
 * host {@code centos.example.com} which accepts SSL connections on port 1636.
 * The examples to BIND using {@code uid=user.1,ou=people,dc=example,dc=com}
 * as the DN and {@code password} as the password.
 *
 *
 *
 * @author Terry J. Gardner
 */
@Since("Dec 11, 2011")
@CodeVersion("1.4")
public final class SimpleBindExample {

  /**
   * @param args
   *   unused and ignored
   */
  public static final void main(final String... args) {

    // Use no key manager, and trust all certificates. This would not be used
    // in non-trivial code.
    SSLUtil sslUtil = new SSLUtil(null,new TrustAllTrustManager());

    SocketFactory socketFactory;
    LDAPConnection ldapConnection = null;
    try {

      // Create the socket factory that will be used to make a secure
      // connection to the server.
      socketFactory = sslUtil.createSSLSocketFactory();
      ldapConnection = new LDAPConnection(socketFactory,HOSTNAME,PORT);

    } catch(LDAPException ldapException) {

      System.err.println(ldapException);
      System.exit(ldapException.getResultCode().intValue());

    } catch(GeneralSecurityException exception) {

      System.err.println(exception);
      System.exit(1);

    }

    try {

      String dn = "uid=user.1,ou=people,dc=example,dc=com";
      String password = "password";
      long maxResponseTimeMillis = 1000;

      BindRequest bindRequest = new SimpleBindRequest(dn,password);
      bindRequest.setResponseTimeoutMillis(maxResponseTimeMillis);
      BindResult bindResult = ldapConnection.bind(bindRequest);

      // Check for the password expiring or password expired
      // response controls
      PasswordExpiredControl pwdExpired =
        PasswordExpiredControl.get(bindResult);
      if(pwdExpired == null) {
        System.out.println("The password expired control was not included in " +
          "the BIND response.");
      } else {
        System.err.println("WARNING:  You must change your password " +
          "before you will be allowed to perform any other operations.");
      }

      PasswordExpiringControl pwdExpiring =
        PasswordExpiringControl.get(bindResult);
      if(pwdExpiring == null) {
        System.out.println("The password expiring control was not included in" +
          " the BIND response.");
      } else {
        System.err.println("WARNING:  Your password will expire in " +
          pwdExpiring.getSecondsUntilExpiration() + " seconds.");
      }

      ldapConnection.close();
      System.out.println(bindResult);

    } catch(LDAPException ldapException) {

      ldapConnection.close();
      System.err.println(ldapException);
      System.exit(ldapException.getResultCode().intValue());

    }
  }


  /**
   * The hostname of IP address where the server listens for client
   * connections.
   */
  public static final String HOSTNAME = "centos.example.com";


  /**
   * The port on which the server listens for client connections.
   */
  public static final int PORT = 1636;

}

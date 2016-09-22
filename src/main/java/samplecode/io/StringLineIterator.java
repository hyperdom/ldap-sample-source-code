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

package samplecode.io;


import com.unboundid.util.Validator;
import samplecode.annotation.Author;
import samplecode.annotation.CodeVersion;
import samplecode.annotation.Since;
import samplecode.listener.ExceptionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;


/**
 * Services provided: a method to iterate over the lines in a file.
 * <p/>
 * usage example: <blockquote>
 * <p/>
 * <pre>
 * StringLineIterator sli = new StringLineIterator(inputStream,exceptionListeners);
 * Iterator&lt;String&gt; iterator = sli.iterator();
 * while(iterator.hasNext())
 * {
 *   String line = iterator.next();
 * }
 * </pre>
 * <p/>
 * </blockquote>
 */
@Author("terry.gardner@unboundid.com")
@Since("Dec 30, 2011")
@CodeVersion("1.0")
public class StringLineIterator {

  /**
   * An iterator used for iterating over the lines in a file
   *
   * @return an interator.
   */
  public Iterator<String> iterator() {
    return new Iterator<String>() {

      /**
       * {@inheritDoc}
       */
      @Override
      public boolean hasNext() {
        try {
          line = bufferedReader.readLine();
        } catch(final IOException iox) {
          if(exceptionListeners != null) {
            for(final ExceptionListener<IOException> l : exceptionListeners) {
              if(l.invoke()) {
                l.processException(iox);
              }
            }
          }
          line = null;
        }
        return line != null;
      }



      /**
       * {@inheritDoc}
       */
      @Override
      public String next() {
        return line;
      }



      /**
       * {@inheritDoc}
       * <p>
       * This functionality is not supported.
       */
      @Override
      public void remove() {
        // TODO: This block deliberately left empty
      }

    };
  }



  /**
   * @param inputStream
   *   the input stream over which to iterate
   * @param exceptionListeners
   *   if not {@code null}, a list of exception listeners to
   *   inform in the event of an exception.
   */
  public StringLineIterator(
    final InputStream inputStream,
    final List<ExceptionListener<IOException>> exceptionListeners) {
    this.exceptionListeners = exceptionListeners;
    Validator.ensureNotNullWithMessage(inputStream,"A null input stream is not permitted.");
    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
  }



  private final BufferedReader bufferedReader;


  private final List<ExceptionListener<IOException>> exceptionListeners;


  private String line = null;
}

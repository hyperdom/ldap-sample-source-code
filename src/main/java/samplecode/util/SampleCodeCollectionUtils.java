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

package samplecode.util;


import samplecode.annotation.Author;
import samplecode.annotation.CodeVersion;
import samplecode.annotation.Since;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import static com.unboundid.util.Validator.ensureNotNull;


/**
 * Utility methods for dealing with collections.
 * This class cannot be instantiated.
 */
@Author("terry.gardner@unboundid.com")
@Since("01-Jan-2008")
@CodeVersion("1.2")
public abstract class SampleCodeCollectionUtils {

  /**
   * Given a {@link String} that consists of strings delimited by a
   * comma, returns a list wherein each member of the list was a string
   * delimited by a comma in the original string.
   *
   * @param csv
   *   A comma-separated list of strings.
   *
   * @return Never returns {@code null}, that is, a list is always
   *         returned (but it might be empty.)
   */
  @SuppressWarnings("unused")
  public static List<String> csvToList(final String csv) {

    List<String> result;
    if(csv != null) {
      result = SampleCodeCollectionUtils.newArrayList();
      final StringTokenizer t = new StringTokenizer(csv,",");
      while(t.hasMoreTokens()) {
        result.add(t.nextToken());
      }
    } else {
      result = Collections.emptyList();
    }
    return result;
  }



  /**
   * Returns a new {@link ArrayList} in a type-safe way.
   *
   * @param <T>
   *   The type of each element of the list.
   *
   * @return A new ArrayList.
   */
  public static <T> List<T> newArrayList() {
    return new ArrayList<T>();
  }



  /**
   * Returns a new {@link ArrayList} in a type-safe way.
   *
   * @param <T>
   *   The type of each element of the list.
   * @param size
   *   The initial size of the list.
   *
   * @return A new ArrayList.
   */
  public static <T> List<T> newArrayList(final int size) {
    return new ArrayList<T>(size);
  }



  /**
   * Returns a new {@link ConcurrentHashMap} in a type-safe way.
   *
   * @param <K>
   *   The type of the keys in the map.
   * @param <V>
   *   The type of values in the map.
   *
   * @return A new ConcurrentHashMap.
   */
  @SuppressWarnings("unused")
  public static <K,V> Map<K,V> newConcurrentHashMap() {
    return new ConcurrentHashMap<K,V>();
  }



  /**
   * Returns a new {@link EnumMap} in a type-safe way.
   *
   * @param <K>
   *   The type of the keys in the map.
   * @param <V>
   *   The type of values in the map.
   * @param keyClass
   *   the class object of the key type for this enum map.
   *
   * @return A new enum map.
   */
  @SuppressWarnings("unused")
  public static <K extends Enum<K>,V> Map<K,V>
  newEnumMap(final Class<K> keyClass) {
    return new EnumMap<K,V>(keyClass);
  }



  /**
   * Returns a new {@link HashMap} in a type-safe way.
   *
   * @param <K>
   *   The type of the keys in the map.
   * @param <V>
   *   The type of values in the map.
   *
   * @return A new HashMap.
   */
  @SuppressWarnings("unused")
  public static <K,V> Map<K,V> newHashMap() {
    return new HashMap<K,V>();
  }



  /**
   * Returns a new {@link HashSet} in a type-safe way.
   *
   * @param <E>
   *   The type of the objects in the set.
   *
   * @return A new HashSet.
   */
  @SuppressWarnings("unused")
  public static <E> Set<E> newHashSet() {
    return new HashSet<E>();
  }



  /**
   * Returns a new {@link HashSet} in a type-safe way.
   *
   * @param <E>
   *   The type of the objects in the set.
   * @param c
   *   a collection of objects that will be used to create the
   *   set.
   *
   * @return A new HashSet.
   */
  @SuppressWarnings("unused")
  public static <E> Set<E> newHashSet(final Collection<E> c) {
    return new HashSet<E>(c);
  }



  /**
   * Returns a new {@link HashSet} in a type-safe way.
   *
   * @param <E>
   *   The type of the objects in the set.
   *
   * @return A new HashSet.
   */
  @SuppressWarnings("unused")
  public static <E> Set<E> newSortedSet() {
    return new ConcurrentSkipListSet<E>();
  }



  /**
   * Creates a new ArrayList from the provided list,
   * which may not be {@code null}.
   */
  public static <T> List<T> newArrayList(final List<T> list) {
    ensureNotNull(list);

    return new ArrayList<T>(list);
  }
}

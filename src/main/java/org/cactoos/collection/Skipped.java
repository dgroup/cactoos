/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2018 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.collection;

import java.util.Collection;
import org.cactoos.iterable.IterableOf;

/**
 * Skipped collection.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @param <T> Type of source item
 * @since 0.34
 */
public final class Skipped<T> extends CollectionEnvelope<T> {

    /**
     * Ctor.
     * @param skip How many to skip
     * @param src Source elements
     */
    @SafeVarargs
    public Skipped(final int skip, final T... src) {
        this(new IterableOf<>(src), skip);
    }

    /**
     * Ctor.
     * @param src Source iterable
     * @param skip How many to skip
     */
    public Skipped(final Iterable<T> src, final int skip) {
        this(new CollectionOf<T>(src), skip);
    }

    /**
     * Ctor.
     * @param src Source collection
     * @param skip How many to skip
     */
    public Skipped(final Collection<T> src, final int skip) {
        super(() -> new CollectionOf<T>(
            new org.cactoos.iterable.Skipped<T>(src, skip)
        ));
    }
}

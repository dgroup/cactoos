/**
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

package org.cactoos.bytes;

import java.io.IOException;
import org.cactoos.Bytes;
import org.cactoos.Text;
import org.cactoos.text.FormattedText;

/**
 * Decodes origin {@link Text} using the hexadecimal encoding scheme.
 *
 * @author Alexander Menshikov (sharplermc@gmail.com)
 * @version $Id$
 * @since 0.29
 * @checkstyle MagicNumberCheck (500 lines)
 */
public final class HexBytes implements Bytes {
    /**
     * Origin hexadecimal text.
     */
    private final Text origin;

    /**
     * Ctor.
     *
     * @param origin Hexadecimal text.
     */
    public HexBytes(final Text origin) {
        this.origin = origin;
    }

    @Override
    public byte[] asBytes() throws IOException {
        final String hex = this.origin.asString();
        if ((hex.length() & 1) == 1) {
            throw new IOException("Length of hexadecimal text is odd");
        }
        final byte[] result = new byte[hex.length() / 2];
        int index = 0;
        while (index < hex.length()) {
            final int most = this.checkedDigit(hex.charAt(index));
            final int less = this.checkedDigit(hex.charAt(index + 1));
            result[index >>> 1] = (byte) ((most << 4) + less);
            index += 2;
        }
        return result;
    }

    /**
     * Wrap around {@link Character#digit(char, int)}.
     *
     * @param character Hexadecimal character.
     * @return Numeric Value of the hex character.
     * @throws IOException If fails.
     */
    private int checkedDigit(final char character) throws IOException {
        final int result = Character.digit(character, 16);
        if (result == -1) {
            throw new IOException(
                new FormattedText(
                    "Unexpected character '%c' in hex string '%s'",
                    character,
                    this.origin
                ).asString()
            );
        }
        return result;
    }
}

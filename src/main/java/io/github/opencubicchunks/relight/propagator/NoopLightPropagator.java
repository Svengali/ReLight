/*
 *  This file is part of ReLight, licensed under the MIT License (MIT).
 *
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package io.github.opencubicchunks.relight.propagator;

import io.github.opencubicchunks.relight.util.Vec3List;
import io.github.opencubicchunks.relight.world.LightDataReader;
import io.github.opencubicchunks.relight.world.LightDataWriter;
import io.github.opencubicchunks.relight.util.LightType;

import java.util.EnumSet;

public class NoopLightPropagator implements LightPropagator {

    private final LightDataReader reader;
    private final LightDataWriter writer;

    public NoopLightPropagator(LightDataReader reader, LightDataWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override public void update(Vec3List posList, EnumSet<LightType> types) {
        while (posList.next()) {
            int x = posList.getX();
            int y = posList.getY();
            int z = posList.getZ();
            for (LightType type : types) {
                writer.setLight(x, y, z, reader.getLightSource(x, y, z, type), type);
            }
        }
    }
}

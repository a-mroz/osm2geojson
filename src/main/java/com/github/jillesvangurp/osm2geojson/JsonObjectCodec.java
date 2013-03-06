/**
 * Copyright (c) 2013, Jilles van Gurp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.jillesvangurp.osm2geojson;

import com.github.jillesvangurp.persistentcachingmap.PersistentCachingMapCodec;
import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;

final class JsonObjectCodec implements PersistentCachingMapCodec<Long, JsonObject> {
    private final JsonParser jsonParser;

    final int bucketSize;
    public JsonObjectCodec(JsonParser jsonParser, int bucketSize) {
        this.jsonParser = jsonParser;
        this.bucketSize = bucketSize;
    }

    @Override
    public Long deserializeKey(String s) {
        return Long.valueOf(s);
    }

    @Override
    public String serializeKey(Long key) {
        return ""+key;
    }

    @Override
    public long bucketId(Long key) {
        return key/bucketSize;
    }

    @Override
    public JsonObject deserializeValue(String s) {
        return jsonParser.parse(s).asObject();
    }

    @Override
    public String serializeValue(JsonObject value) {
        return value.toString();
    }
}
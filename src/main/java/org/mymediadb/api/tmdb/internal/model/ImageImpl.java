/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mymediadb.api.tmdb.internal.model;

import org.apache.log4j.Logger;
import org.mymediadb.api.tmdb.model.Image;

public class ImageImpl implements Image {

    private final static Logger log = Logger.getLogger(ImageImpl.class);

    private String type;
    private String size;
    private Integer height;
    private Integer width;
    private String url;
    private String id;

    public ImageImpl(String type, String size, Integer height, Integer width, String url, String id) {
        this.type = type;
        this.size = size;
        this.height = height;
        this.width = width;
        this.url = url;
        this.id = id;
    }

    @Override
    public Type getType() {
        try {
            return Image.Type.valueOf(this.type.toUpperCase());
        } catch (IllegalArgumentException x) {
            log.warn("The specified enum name " + this.type + " was not found!", x);
            return null;
        }
    }

    @Override
    public Size getSize() {
        try {
            return Image.Size.valueOf(this.size.toUpperCase());
        } catch (IllegalArgumentException x) {
            log.warn("The specified enum name " + this.size + " was not found!", x);
            return null;
        }
    }

    @Override
    public Integer getHeight() {
        return this.height;
    }

    @Override
    public Integer getWidth() {
        return this.width;
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ImageImpl{" +
                "height=" + height +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", width=" + width +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2011  Kaltura Inc.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// @ignore
// ===================================================================================================
package com.kaltura.client.enums;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Fri, 17 Aug 12 06:33:26 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */
public enum KalturaVideoCodec implements KalturaEnumAsString {
    NONE (""),
    VP6 ("vp6"),
    H263 ("h263"),
    H264 ("h264"),
    H264B ("h264b"),
    H264M ("h264m"),
    H264H ("h264h"),
    FLV ("flv"),
    MPEG4 ("mpeg4"),
    THEORA ("theora"),
    WMV2 ("wmv2"),
    WMV3 ("wmv3"),
    WVC1A ("wvc1a"),
    VP8 ("vp8"),
    MPEG2 ("mpeg2"),
    COPY ("copy");

    public String hashCode;

    KalturaVideoCodec(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaVideoCodec get(String hashCode) {
        if (hashCode.equals(""))
        {
           return NONE;
        }
        else 
        if (hashCode.equals("vp6"))
        {
           return VP6;
        }
        else 
        if (hashCode.equals("h263"))
        {
           return H263;
        }
        else 
        if (hashCode.equals("h264"))
        {
           return H264;
        }
        else 
        if (hashCode.equals("h264b"))
        {
           return H264B;
        }
        else 
        if (hashCode.equals("h264m"))
        {
           return H264M;
        }
        else 
        if (hashCode.equals("h264h"))
        {
           return H264H;
        }
        else 
        if (hashCode.equals("flv"))
        {
           return FLV;
        }
        else 
        if (hashCode.equals("mpeg4"))
        {
           return MPEG4;
        }
        else 
        if (hashCode.equals("theora"))
        {
           return THEORA;
        }
        else 
        if (hashCode.equals("wmv2"))
        {
           return WMV2;
        }
        else 
        if (hashCode.equals("wmv3"))
        {
           return WMV3;
        }
        else 
        if (hashCode.equals("wvc1a"))
        {
           return WVC1A;
        }
        else 
        if (hashCode.equals("vp8"))
        {
           return VP8;
        }
        else 
        if (hashCode.equals("mpeg2"))
        {
           return MPEG2;
        }
        else 
        if (hashCode.equals("copy"))
        {
           return COPY;
        }
        else 
        {
           return NONE;
        }
    }
}

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
package com.kaltura.client.types;

import org.w3c.dom.Element;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaObjectBase;
import com.kaltura.client.enums.KalturaDropFolderFileStatus;
import com.kaltura.client.enums.KalturaDropFolderFileErrorCode;
import com.kaltura.client.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Fri, 17 Aug 12 06:33:26 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

public class KalturaDropFolderFile extends KalturaObjectBase {
    public int id = Integer.MIN_VALUE;
    public int partnerId = Integer.MIN_VALUE;
    public int dropFolderId = Integer.MIN_VALUE;
    public String fileName;
    public float fileSize = Float.MIN_VALUE;
    public int fileSizeLastSetAt = Integer.MIN_VALUE;
    public KalturaDropFolderFileStatus status;
    public String parsedSlug;
    public String parsedFlavor;
    public KalturaDropFolderFileErrorCode errorCode;
    public String errorDescription;
    public String lastModificationTime;
    public int createdAt = Integer.MIN_VALUE;
    public int updatedAt = Integer.MIN_VALUE;

    public KalturaDropFolderFile() {
    }

    public KalturaDropFolderFile(Element node) throws KalturaApiException {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("id")) {
                this.id = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerId")) {
                this.partnerId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("dropFolderId")) {
                this.dropFolderId = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("fileName")) {
                this.fileName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("fileSize")) {
                this.fileSize = ParseUtils.parseFloat(txt);
                continue;
            } else if (nodeName.equals("fileSizeLastSetAt")) {
                this.fileSizeLastSetAt = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("status")) {
                this.status = KalturaDropFolderFileStatus.get(ParseUtils.parseInt(txt));
                continue;
            } else if (nodeName.equals("parsedSlug")) {
                this.parsedSlug = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("parsedFlavor")) {
                this.parsedFlavor = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("errorCode")) {
                this.errorCode = KalturaDropFolderFileErrorCode.get(ParseUtils.parseString(txt));
                continue;
            } else if (nodeName.equals("errorDescription")) {
                this.errorDescription = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("lastModificationTime")) {
                this.lastModificationTime = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("createdAt")) {
                this.createdAt = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("updatedAt")) {
                this.updatedAt = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaDropFolderFile");
        kparams.add("dropFolderId", this.dropFolderId);
        kparams.add("fileName", this.fileName);
        kparams.add("fileSize", this.fileSize);
        kparams.add("parsedSlug", this.parsedSlug);
        kparams.add("parsedFlavor", this.parsedFlavor);
        kparams.add("errorCode", this.errorCode);
        kparams.add("errorDescription", this.errorDescription);
        kparams.add("lastModificationTime", this.lastModificationTime);
        return kparams;
    }

}


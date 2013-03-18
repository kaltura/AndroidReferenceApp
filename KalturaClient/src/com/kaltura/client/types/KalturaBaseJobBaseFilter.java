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

public abstract class KalturaBaseJobBaseFilter extends KalturaFilter {
    public int idEqual = Integer.MIN_VALUE;
    public int idGreaterThanOrEqual = Integer.MIN_VALUE;
    public int partnerIdEqual = Integer.MIN_VALUE;
    public String partnerIdIn;
    public String partnerIdNotIn;
    public int createdAtGreaterThanOrEqual = Integer.MIN_VALUE;
    public int createdAtLessThanOrEqual = Integer.MIN_VALUE;
    public int updatedAtGreaterThanOrEqual = Integer.MIN_VALUE;
    public int updatedAtLessThanOrEqual = Integer.MIN_VALUE;
    public int processorExpirationGreaterThanOrEqual = Integer.MIN_VALUE;
    public int processorExpirationLessThanOrEqual = Integer.MIN_VALUE;
    public int executionAttemptsGreaterThanOrEqual = Integer.MIN_VALUE;
    public int executionAttemptsLessThanOrEqual = Integer.MIN_VALUE;
    public int lockVersionGreaterThanOrEqual = Integer.MIN_VALUE;
    public int lockVersionLessThanOrEqual = Integer.MIN_VALUE;

    public KalturaBaseJobBaseFilter() {
    }

    public KalturaBaseJobBaseFilter(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("idEqual")) {
                this.idEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("idGreaterThanOrEqual")) {
                this.idGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerIdEqual")) {
                this.partnerIdEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("partnerIdIn")) {
                this.partnerIdIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("partnerIdNotIn")) {
                this.partnerIdNotIn = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("createdAtGreaterThanOrEqual")) {
                this.createdAtGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("createdAtLessThanOrEqual")) {
                this.createdAtLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("updatedAtGreaterThanOrEqual")) {
                this.updatedAtGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("updatedAtLessThanOrEqual")) {
                this.updatedAtLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("processorExpirationGreaterThanOrEqual")) {
                this.processorExpirationGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("processorExpirationLessThanOrEqual")) {
                this.processorExpirationLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("executionAttemptsGreaterThanOrEqual")) {
                this.executionAttemptsGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("executionAttemptsLessThanOrEqual")) {
                this.executionAttemptsLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lockVersionGreaterThanOrEqual")) {
                this.lockVersionGreaterThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } else if (nodeName.equals("lockVersionLessThanOrEqual")) {
                this.lockVersionLessThanOrEqual = ParseUtils.parseInt(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaBaseJobBaseFilter");
        kparams.add("idEqual", this.idEqual);
        kparams.add("idGreaterThanOrEqual", this.idGreaterThanOrEqual);
        kparams.add("partnerIdEqual", this.partnerIdEqual);
        kparams.add("partnerIdIn", this.partnerIdIn);
        kparams.add("partnerIdNotIn", this.partnerIdNotIn);
        kparams.add("createdAtGreaterThanOrEqual", this.createdAtGreaterThanOrEqual);
        kparams.add("createdAtLessThanOrEqual", this.createdAtLessThanOrEqual);
        kparams.add("updatedAtGreaterThanOrEqual", this.updatedAtGreaterThanOrEqual);
        kparams.add("updatedAtLessThanOrEqual", this.updatedAtLessThanOrEqual);
        kparams.add("processorExpirationGreaterThanOrEqual", this.processorExpirationGreaterThanOrEqual);
        kparams.add("processorExpirationLessThanOrEqual", this.processorExpirationLessThanOrEqual);
        kparams.add("executionAttemptsGreaterThanOrEqual", this.executionAttemptsGreaterThanOrEqual);
        kparams.add("executionAttemptsLessThanOrEqual", this.executionAttemptsLessThanOrEqual);
        kparams.add("lockVersionGreaterThanOrEqual", this.lockVersionGreaterThanOrEqual);
        kparams.add("lockVersionLessThanOrEqual", this.lockVersionLessThanOrEqual);
        return kparams;
    }

}


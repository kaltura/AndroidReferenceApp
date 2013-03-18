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
import java.util.ArrayList;
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

public class KalturaLiveStreamEntry extends KalturaMediaEntry {
	/**  The message to be presented when the stream is offline     */
    public String offlineMessage;
	/**  The stream id as provided by the provider     */
    public String streamRemoteId;
	/**  The backup stream id as provided by the provider     */
    public String streamRemoteBackupId;
	/**  Array of supported bitrates     */
    public ArrayList<KalturaLiveStreamBitrate> bitrates;
    public String primaryBroadcastingUrl;
    public String secondaryBroadcastingUrl;
    public String streamName;
	/**  The stream url     */
    public String streamUrl;

    public KalturaLiveStreamEntry() {
    }

    public KalturaLiveStreamEntry(Element node) throws KalturaApiException {
        super(node);
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node aNode = childNodes.item(i);
            String nodeName = aNode.getNodeName();
            String txt = aNode.getTextContent();
            if (nodeName.equals("offlineMessage")) {
                this.offlineMessage = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("streamRemoteId")) {
                this.streamRemoteId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("streamRemoteBackupId")) {
                this.streamRemoteBackupId = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("bitrates")) {
                this.bitrates = ParseUtils.parseArray(KalturaLiveStreamBitrate.class, aNode);
                continue;
            } else if (nodeName.equals("primaryBroadcastingUrl")) {
                this.primaryBroadcastingUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("secondaryBroadcastingUrl")) {
                this.secondaryBroadcastingUrl = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("streamName")) {
                this.streamName = ParseUtils.parseString(txt);
                continue;
            } else if (nodeName.equals("streamUrl")) {
                this.streamUrl = ParseUtils.parseString(txt);
                continue;
            } 
        }
    }

    public KalturaParams toParams() {
        KalturaParams kparams = super.toParams();
        kparams.add("objectType", "KalturaLiveStreamEntry");
        kparams.add("offlineMessage", this.offlineMessage);
        kparams.add("bitrates", this.bitrates);
        kparams.add("primaryBroadcastingUrl", this.primaryBroadcastingUrl);
        kparams.add("secondaryBroadcastingUrl", this.secondaryBroadcastingUrl);
        kparams.add("streamName", this.streamName);
        kparams.add("streamUrl", this.streamUrl);
        return kparams;
    }

}


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
package com.kaltura.client.services;

import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaServiceBase;
import org.w3c.dom.Element;
import com.kaltura.client.utils.ParseUtils;
import com.kaltura.client.KalturaParams;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.*;

/**
 * This class was generated using generate.php
 * against an XML schema provided by Kaltura.
 * @date Fri, 17 Aug 12 06:33:26 -0400
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

/**  Kaltura Sharepoint Extension Service    */
public class KalturaSharepointExtensionService extends KalturaServiceBase {
    public KalturaSharepointExtensionService(KalturaClient client) {
        this.kalturaClient = client;
    }

	/**  Is this Kaltura-Sharepoint-Server-Plugin supports minimum version of
	  $major.$minor.$build (which is required by the extension)     */
    public boolean isVersionSupported(int serverMajor, int serverMinor, int serverBuild) throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        kparams.add("serverMajor", serverMajor);
        kparams.add("serverMinor", serverMinor);
        kparams.add("serverBuild", serverBuild);
        this.kalturaClient.queueServiceCall("kalturasharepointextension_sharepointextension", "isVersionSupported", kparams);
        if (this.kalturaClient.isMultiRequest())
            return false;
        Element resultXmlElement = this.kalturaClient.doQueue();
        String resultText = resultXmlElement.getTextContent();
        return ParseUtils.parseBool(resultText);
    }

	/**  list uiconfs for sharepoint extension     */
    public KalturaUiConfListResponse listUiconfs() throws KalturaApiException {
        KalturaParams kparams = new KalturaParams();
        this.kalturaClient.queueServiceCall("kalturasharepointextension_sharepointextension", "listUiconfs", kparams);
        if (this.kalturaClient.isMultiRequest())
            return null;
        Element resultXmlElement = this.kalturaClient.doQueue();
        return ParseUtils.parseObject(KalturaUiConfListResponse.class, resultXmlElement);
    }
}

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
public enum KalturaBatchJobOrderBy implements KalturaEnumAsString {
    STATUS_ASC ("+status"),
    STATUS_DESC ("-status"),
    CHECK_AGAIN_TIMEOUT_ASC ("+checkAgainTimeout"),
    CHECK_AGAIN_TIMEOUT_DESC ("-checkAgainTimeout"),
    PROGRESS_ASC ("+progress"),
    PROGRESS_DESC ("-progress"),
    UPDATES_COUNT_ASC ("+updatesCount"),
    UPDATES_COUNT_DESC ("-updatesCount"),
    PRIORITY_ASC ("+priority"),
    PRIORITY_DESC ("-priority"),
    QUEUE_TIME_ASC ("+queueTime"),
    QUEUE_TIME_DESC ("-queueTime"),
    FINISH_TIME_ASC ("+finishTime"),
    FINISH_TIME_DESC ("-finishTime"),
    FILE_SIZE_ASC ("+fileSize"),
    FILE_SIZE_DESC ("-fileSize"),
    CREATED_AT_ASC ("+createdAt"),
    CREATED_AT_DESC ("-createdAt"),
    UPDATED_AT_ASC ("+updatedAt"),
    UPDATED_AT_DESC ("-updatedAt"),
    PROCESSOR_EXPIRATION_ASC ("+processorExpiration"),
    PROCESSOR_EXPIRATION_DESC ("-processorExpiration"),
    EXECUTION_ATTEMPTS_ASC ("+executionAttempts"),
    EXECUTION_ATTEMPTS_DESC ("-executionAttempts"),
    LOCK_VERSION_ASC ("+lockVersion"),
    LOCK_VERSION_DESC ("-lockVersion");

    public String hashCode;

    KalturaBatchJobOrderBy(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getHashCode() {
        return this.hashCode;
    }

    public static KalturaBatchJobOrderBy get(String hashCode) {
        if (hashCode.equals("+status"))
        {
           return STATUS_ASC;
        }
        else 
        if (hashCode.equals("-status"))
        {
           return STATUS_DESC;
        }
        else 
        if (hashCode.equals("+checkAgainTimeout"))
        {
           return CHECK_AGAIN_TIMEOUT_ASC;
        }
        else 
        if (hashCode.equals("-checkAgainTimeout"))
        {
           return CHECK_AGAIN_TIMEOUT_DESC;
        }
        else 
        if (hashCode.equals("+progress"))
        {
           return PROGRESS_ASC;
        }
        else 
        if (hashCode.equals("-progress"))
        {
           return PROGRESS_DESC;
        }
        else 
        if (hashCode.equals("+updatesCount"))
        {
           return UPDATES_COUNT_ASC;
        }
        else 
        if (hashCode.equals("-updatesCount"))
        {
           return UPDATES_COUNT_DESC;
        }
        else 
        if (hashCode.equals("+priority"))
        {
           return PRIORITY_ASC;
        }
        else 
        if (hashCode.equals("-priority"))
        {
           return PRIORITY_DESC;
        }
        else 
        if (hashCode.equals("+queueTime"))
        {
           return QUEUE_TIME_ASC;
        }
        else 
        if (hashCode.equals("-queueTime"))
        {
           return QUEUE_TIME_DESC;
        }
        else 
        if (hashCode.equals("+finishTime"))
        {
           return FINISH_TIME_ASC;
        }
        else 
        if (hashCode.equals("-finishTime"))
        {
           return FINISH_TIME_DESC;
        }
        else 
        if (hashCode.equals("+fileSize"))
        {
           return FILE_SIZE_ASC;
        }
        else 
        if (hashCode.equals("-fileSize"))
        {
           return FILE_SIZE_DESC;
        }
        else 
        if (hashCode.equals("+createdAt"))
        {
           return CREATED_AT_ASC;
        }
        else 
        if (hashCode.equals("-createdAt"))
        {
           return CREATED_AT_DESC;
        }
        else 
        if (hashCode.equals("+updatedAt"))
        {
           return UPDATED_AT_ASC;
        }
        else 
        if (hashCode.equals("-updatedAt"))
        {
           return UPDATED_AT_DESC;
        }
        else 
        if (hashCode.equals("+processorExpiration"))
        {
           return PROCESSOR_EXPIRATION_ASC;
        }
        else 
        if (hashCode.equals("-processorExpiration"))
        {
           return PROCESSOR_EXPIRATION_DESC;
        }
        else 
        if (hashCode.equals("+executionAttempts"))
        {
           return EXECUTION_ATTEMPTS_ASC;
        }
        else 
        if (hashCode.equals("-executionAttempts"))
        {
           return EXECUTION_ATTEMPTS_DESC;
        }
        else 
        if (hashCode.equals("+lockVersion"))
        {
           return LOCK_VERSION_ASC;
        }
        else 
        if (hashCode.equals("-lockVersion"))
        {
           return LOCK_VERSION_DESC;
        }
        else 
        {
           return STATUS_ASC;
        }
    }
}

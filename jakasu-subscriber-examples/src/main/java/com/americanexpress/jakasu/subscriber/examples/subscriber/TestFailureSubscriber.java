/*
 *
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.americanexpress.jakasu.subscriber.examples.subscriber;

import com.americanexpress.jakasu.subscriber.exceptions.SubscriberException;
import com.americanexpress.jakasu.subscriber.subscribe.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TestFailureSubscriber implements Subscriber {
    /* Example subscriber for testing failure flow
     * A SubscriberException is thrown for every message,
     * which should cause the handleError method to be called and
     * log that an error occurred
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestFailureSubscriber.class);


    @Override
    public boolean recoveryHandler(String payload, Map<String, String> headers) {
        LOGGER.error("Error occurred while processing message in recovery handler: {} {}", payload, headers);
        return true;
    }

    @Override
    public void handleError(SubscriberException ex) {
        LOGGER.error("Error occurred while processing message: {}", ex);
    }

    @Override
    public boolean subscribe(String payload, Map<String, String> headers) throws SubscriberException {
        LOGGER.info(payload);
        throw new SubscriberException("Testing error", new Exception());
    }
}


/*
 * Copyright 2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.sample;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelArtemisRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {


        from("timer:mytimer?period=5000").routeId("generate-route")
                .transform(constant("HELLO from Camel!"))
                .to("jms:queue:QueueIN");


        from("jms:queue:QueueIN").routeId("receive-route")
                .log("Received a message - ${body} - sending to outbound queue")
                .to("jms:queue:QueueOUT?exchangePattern=InOnly");


    }
}
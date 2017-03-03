/**
 * Copyright © 2017 Florian Troßbach (trossbach@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ftrossbach.kiqr.core.query.facade;

import com.github.ftrossbach.kiqr.commons.config.Config;
import com.github.ftrossbach.kiqr.commons.config.querymodel.requests.*;
import io.vertx.core.AbstractVerticle;

/**
 * Created by ftr on 22/02/2017.
 */
public class WindowedQueryFacadeVerticle extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer(Config.WINDOWED_QUERY_FACADE_ADDRESS, msg -> {
            WindowedQuery query = (WindowedQuery) msg.body();
            InstanceResolverQuery instanceQuery = new InstanceResolverQuery(query.getStoreName(), query.getKeySerde(), query.getKey() );
            vertx.eventBus().send(Config.INSTANCE_RESOLVER_ADDRESS_SINGLE, instanceQuery, reply -> {
                if(reply.succeeded()){

                    InstanceResolverResponse response = (InstanceResolverResponse) reply.result().body();
                    vertx.eventBus().send(Config.WINDOWED_QUERY_ADDRESS_PREFIX + response.getInstanceId().get(), query, rep -> {

                        if(rep.failed()){
                            rep.cause().printStackTrace();
                            msg.fail(-1, rep.cause().getMessage());
                        }
                        else {
                            WindowedQueryResponse queryResponse = (WindowedQueryResponse) rep.result().body();
                            msg.reply(queryResponse);
                        }


                    });
                } else {
                  msg.fail(-1, reply.cause().getMessage());
                }

            });
        });
    }
}

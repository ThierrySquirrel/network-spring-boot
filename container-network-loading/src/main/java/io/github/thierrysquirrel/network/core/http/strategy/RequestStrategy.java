/**
 * Copyright 2026/6/2 ThierrySquirrel
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.github.thierrysquirrel.network.core.http.strategy;


import io.github.thierrysquirrel.network.annotation.DeleteRequest;
import io.github.thierrysquirrel.network.annotation.GetRequest;
import io.github.thierrysquirrel.network.annotation.PostRequest;
import io.github.thierrysquirrel.network.annotation.PutRequest;
import io.github.thierrysquirrel.network.core.http.builder.RequestBuilder;
import io.github.thierrysquirrel.network.error.NetworkException;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ClassName: RequestStrategy
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class RequestStrategy {
    private RequestStrategy() {
    }

    public static <T> Request createRequest(T annotation, Request.Builder builder, RequestBody requestBody) throws NetworkException {
        if (annotation instanceof GetRequest) {
            RequestBuilder.builderGet(builder);
        }
        if (annotation instanceof PostRequest) {
            RequestBuilder.builderPost(builder, requestBody);
        }
        if (annotation instanceof PutRequest) {
            RequestBuilder.builderPut(builder, requestBody);
        }
        if (annotation instanceof DeleteRequest) {
            RequestBuilder.builderDelete(builder, requestBody);
        }
        return builder.build();
    }
}

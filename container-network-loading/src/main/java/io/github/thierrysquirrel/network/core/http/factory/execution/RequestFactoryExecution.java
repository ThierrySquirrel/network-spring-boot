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

package io.github.thierrysquirrel.network.core.http.factory.execution;


import io.github.thierrysquirrel.network.core.http.factory.OkHttpClientBuilderFactory;
import io.github.thierrysquirrel.network.core.utils.JsonUtils;
import io.github.thierrysquirrel.network.error.NetworkException;
import io.github.thierrysquirrel.network.loading.NetworkLoading;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: RequestFactoryExecution
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class RequestFactoryExecution {
    private RequestFactoryExecution() {
    }

    public static <T> Object execute(NetworkLoading networkLoading, Request request, Class<T> resultType) throws IOException, NetworkException {

        OkHttpClient okHttpClient = OkHttpClientBuilderFactory.createdOkHttp(networkLoading);
        ResponseBody body = okHttpClient.newCall(request).execute().body();
        if (null == body) {
            throw new NetworkException("body is null");
        }
        if (resultType.equals(InputStream.class)) {
            return body.byteStream();
        }

        String result = body.string();
        if (resultType.equals(String.class)) {
            return result;
        }

        return JsonUtils.fromJson(result, resultType);

    }
}

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

package io.github.thierrysquirrel.network.core.http.factory;

import io.github.thierrysquirrel.network.core.http.builder.RequestBodyBuilder;
import okhttp3.RequestBody;

import java.io.File;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: RequestBodyBuilderFactory
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class RequestBodyBuilderFactory {
    private RequestBodyBuilderFactory() {
    }

    public static RequestBody createdMultipartBody(Map<String, File> fileMap, String body) {
        if (Objects.isNull(fileMap) && Objects.isNull(body)) {
            return null;
        }

        RequestBody requestBody = RequestBodyBuilder.builderFileBody(fileMap);
        if (requestBody == null) {
            requestBody = RequestBodyBuilder.builderBody(body);
        }
        return requestBody;
    }
}

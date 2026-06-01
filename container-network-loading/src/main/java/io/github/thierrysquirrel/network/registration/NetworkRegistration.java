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
package io.github.thierrysquirrel.network.registration;

import io.github.thierrysquirrel.network.annotation.Network;
import io.github.thierrysquirrel.network.loading.NetworkLoading;
import io.github.thierrysquirrel.network.proxy.utils.NetworkProxyUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: NetworkRegistration
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class NetworkRegistration {
    private NetworkRegistration() {
    }

    public static void networkRegistrationScannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {

        NetworkLoading networkLoading = (NetworkLoading) registrationMap.get(NetworkLoading.class);

        for (Class<?> thisClass : scannerClassList) {
            Network network = thisClass.getAnnotation(Network.class);
            if (Objects.isNull(network)) {
                continue;
            }
            Object networkProxy = NetworkProxyUtils.createNetworkProxy(thisClass, networkLoading);
            registrationMap.put(thisClass, networkProxy);
        }
    }
}

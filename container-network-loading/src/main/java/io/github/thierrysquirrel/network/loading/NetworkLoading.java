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
package io.github.thierrysquirrel.network.loading;

import io.github.thierrysquirrel.container.scanner.annotation.ScannerPackage;
import io.github.thierrysquirrel.container.scanner.registration.InterfaceManualRegistration;
import io.github.thierrysquirrel.network.loading.constant.NetworkLoadingConstant;

import java.util.List;
import java.util.Map;

/**
 * ClassName: NetworkLoading
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@ScannerPackage(packageName = "io.github.thierrysquirrel.network.loading")
public class NetworkLoading implements InterfaceManualRegistration {
    private int connectTimeout = NetworkLoadingConstant.DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = NetworkLoadingConstant.DEFAULT_READ_TIMEOUT;
    private int writeTimeout = NetworkLoadingConstant.DEFAULT_WRITE_TIMEOUT;

    @Override
    public void scannerAll(List<Class<?>> list, Map<Class<?>, Object> map) {

    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    @Override
    public String toString() {
        return "NetworkLoading{" +
                "connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", writeTimeout=" + writeTimeout +
                '}';
    }
}

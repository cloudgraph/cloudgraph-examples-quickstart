/**
 * Copyright 2017 TerraMeta Software, Inc.
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
@Alias(physicalName = "HR")
@Namespace(uri = "http://cloudgraph-quickstart-pojo/humanresources")
@NamespaceProvisioning(rootPackageName = "quickstart.pojo.model")
@NamespaceService(storeType = DataStoreType.NOSQL, providerName = DataAccessProviderName.HBASE, properties = {
    "hbase.zookeeper.quorum=zookeeper-host1:2181,zookeeper-host2:2181,zookeeper-host3:2181",
    "hbase.zookeeper.property.clientPort=2181",
    "org.plasma.sdo.access.provider.hbase.ConnectionPoolMinSize=1",
    "org.plasma.sdo.access.provider.hbase.ConnectionPoolMaxSize=80" })
package examples.quickstart.hbase.pojo;

import org.plasma.runtime.DataAccessProviderName;
import org.plasma.runtime.DataStoreType;
import org.plasma.runtime.annotation.NamespaceProvisioning;
import org.plasma.runtime.annotation.NamespaceService;
import org.plasma.sdo.annotation.Alias;
import org.plasma.sdo.annotation.Namespace;


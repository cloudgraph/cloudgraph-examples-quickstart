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
package examples.quickstart.types;

import org.cloudgraph.store.mapping.annotation.Table;
import org.plasma.sdo.DataType;
import org.plasma.sdo.annotation.Alias;
import org.plasma.sdo.annotation.Comment;
import org.plasma.sdo.annotation.DataProperty;
import org.plasma.sdo.annotation.Type;

/**
 * Transaction entity. Note: not bound to a {@link Table} but linked to a
 * {@link Card}, forming a wide row with {@link Card} as the root entity.
 */
@Comment(body = "An example banking transaction entity")
@Alias(physicalName = "T")
@Type
public enum Transaction {

  @Comment(body = "The dollar amount component for the transaction")
  @Alias(physicalName = "D")
  @DataProperty(dataType = DataType.Int)
  dollars,

  @Comment(body = "The cents amount component for the transaction")
  @Alias(physicalName = "C")
  @DataProperty(dataType = DataType.Short)
  cents,

  @Comment(body = "The date the transaction occurred")
  @Alias(physicalName = "TD")
  @DataProperty(dataType = DataType.Date)
  transactionDate,

}

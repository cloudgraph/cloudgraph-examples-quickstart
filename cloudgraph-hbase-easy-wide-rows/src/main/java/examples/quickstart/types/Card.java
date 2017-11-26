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

import org.cloudgraph.store.mapping.annotation.RowKeyField;
import org.cloudgraph.store.mapping.annotation.Table;
import org.plasma.sdo.DataType;
import org.plasma.sdo.annotation.Alias;
import org.plasma.sdo.annotation.Comment;
import org.plasma.sdo.annotation.DataProperty;
import org.plasma.sdo.annotation.EnumConstraint;
import org.plasma.sdo.annotation.ReferenceProperty;
import org.plasma.sdo.annotation.Type;
import org.plasma.sdo.annotation.ValueConstraint;

@Comment(body = "A simple example bank card entity with associated transactions")
@Table(name = "CARD")
@Alias(physicalName = "CD")
@Type
public enum Card {
  @Comment(body = "The card issuer")
  @RowKeyField
  @ValueConstraint(maxLength = "1")
  @EnumConstraint(targetEnum = Issuer.class)
  @Alias(physicalName = "ISU")
  @DataProperty(dataType = DataType.String, isNullable = false)
  issuer,

  @Comment(body = "The card 16 digit (plastic) number")
  @RowKeyField
  @ValueConstraint(maxLength = "16")
  @Alias(physicalName = "NUM")
  @DataProperty(dataType = DataType.String, isNullable = false)
  number,

  @Comment(body = "Links the card to any number of transaction entities")
  @Alias(physicalName = "TNS")
  @ReferenceProperty(isNullable = true, isMany = true, targetClass = Transaction.class, targetProperty = "")
  transaction;
}

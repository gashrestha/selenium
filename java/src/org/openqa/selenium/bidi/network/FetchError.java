// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.bidi.network;

import java.io.StringReader;
import java.util.Map;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.json.JsonInput;

public class FetchError extends BaseParameters {
  private static final Json JSON = new Json();

  private final String errorText;

  private FetchError(BaseParameters baseParameters, String errorText) {
    super(
        baseParameters.getBrowsingContextId(),
        baseParameters.isBlocked(),
        baseParameters.getNavigationId(),
        baseParameters.getRedirectCount(),
        baseParameters.getRequest(),
        baseParameters.getTimestamp(),
        baseParameters.getIntercepts());
    this.errorText = errorText;
  }

  public static FetchError fromJsonMap(Map<String, Object> jsonMap) {
    try (StringReader baseParameterReader = new StringReader(JSON.toJson(jsonMap));
        JsonInput baseParamsInput = JSON.newInput(baseParameterReader)) {
      String errorText = JSON.toJson(jsonMap.get("errorText"));
      return new FetchError(BaseParameters.fromJson(baseParamsInput), errorText);
    }
  }

  public String getErrorText() {
    return errorText;
  }
}

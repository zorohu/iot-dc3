/*
 * Copyright 2018 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pnoker.api.dbs.feign;

import com.pnoker.api.dbs.hystrix.RtmpFeignApiHystrix;
import com.pnoker.common.model.rtmp.Rtmp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Copyright(c) 2018. Pnoker All Rights Reserved.
 * <p>Author     : Pnoker
 * <p>Email      : pnokers@gmail.com
 * <p>Description:
 */
@RequestMapping("/rtmp")
@FeignClient(name = "DC3-DBS", fallbackFactory = RtmpFeignApiHystrix.class)
public interface RtmpFeignApi {

    @GetMapping(value = "/api")
    String api();

    @GetMapping(value = "/list")
    Rtmp list();
}

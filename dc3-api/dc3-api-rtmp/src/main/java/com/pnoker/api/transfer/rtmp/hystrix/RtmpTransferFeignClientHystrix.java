/*
 * Copyright 2019 Pnoker. All Rights Reserved.
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

package com.pnoker.api.transfer.rtmp.hystrix;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pnoker.api.transfer.rtmp.feign.RtmpTransferFeignClient;
import com.pnoker.common.bean.Response;
import com.pnoker.common.dto.transfer.RtmpDto;
import com.pnoker.common.entity.rtmp.Rtmp;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>RtmpTransferFeignClientHystrix
 *
 * @author : pnoker
 * @email : pnokers@icloud.com
 */
@Slf4j
@Component
public class RtmpTransferFeignClientHystrix implements FallbackFactory<RtmpTransferFeignClient> {

    @Override
    public RtmpTransferFeignClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-RTMP" : throwable.getMessage();
        log.error("RtmpTransferFeignClient:{},hystrix服务降级处理", message, throwable);

        return new RtmpTransferFeignClient() {
            @Override
            public Response<Rtmp> add(Rtmp rtmp) {
                return Response.fail(message);
            }

            @Override
            public Response<Boolean> delete(Long id) {
                return Response.fail(message);
            }

            @Override
            public Response<Rtmp> update(Rtmp rtmp) {
                return Response.fail(message);
            }

            @Override
            public Response<Rtmp> selectById(Long id) {
                return Response.fail(message);
            }

            @Override
            public Response<Page<Rtmp>> list(RtmpDto rtmpDto) {
                return Response.fail(message);
            }

            @Override
            public Response<Boolean> start(Long id) {
                return Response.fail(message);
            }

            @Override
            public Response<Boolean> stop(Long id) {
                return Response.fail(message);
            }
        };
    }
}
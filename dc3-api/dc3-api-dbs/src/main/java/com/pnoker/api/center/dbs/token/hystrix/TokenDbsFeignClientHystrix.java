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

package com.pnoker.api.center.dbs.token.hystrix;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pnoker.api.center.dbs.token.feign.TokenDbsFeignClient;
import com.pnoker.common.bean.Response;
import com.pnoker.common.dto.auth.TokenDto;
import com.pnoker.common.entity.auth.Token;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>TokenDbsFeignClientHystrix
 *
 * @author : pnoker
 * @email : pnokers@icloud.com
 */
@Slf4j
@Component
public class TokenDbsFeignClientHystrix implements FallbackFactory<TokenDbsFeignClient> {

    @Override
    public TokenDbsFeignClient create(Throwable throwable) {
        String message = throwable.getMessage() == null ? "No available server for client: DC3-DBS" : throwable.getMessage();
        log.error("TokenDbsFeignClient:{},hystrix服务降级处理", message, throwable);
        return new TokenDbsFeignClient() {
            @Override
            public Response<Token> add(Token token) {
                return Response.fail(message);
            }

            @Override
            public Response<Boolean> delete(Long id) {
                return Response.fail(message);
            }

            @Override
            public Response<Token> update(Token token) {
                return Response.fail(message);
            }

            @Override
            public Response<Token> selectById(Long id) {
                return Response.fail(message);
            }

            @Override
            public Response<Token> selectByUserId(Long id) {
                return Response.fail(message);
            }

            @Override
            public Response<Page<Token>> list(TokenDto tokenDto) {
                return Response.fail(message);
            }
        };
    }
}

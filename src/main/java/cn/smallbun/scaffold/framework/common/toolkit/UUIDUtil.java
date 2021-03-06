/*
 * smallbun-scaffold-framework - smallbun企业级开发脚手架-核心框架
 * Copyright © 2019 SanLi (qinggang.zuo@gmail.com) ${company}
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cn.smallbun.scaffold.framework.common.toolkit;

import java.util.UUID;

/**
 * UUID生成
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/25 20:23
 */
public class UUIDUtil {

    /**
     * 获取UUID
     *
     * @return String
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    /**
     * 获取UUID
     *
     * @return String[]
     */
    public static String[] getUUID(int number) {
        String[] result = null;
        if (number > 0) {
            result = new String[number];
            for (int i = 0; i < number; i++) {
                result[i] = getUUID();
            }
        }
        return result;
    }

}

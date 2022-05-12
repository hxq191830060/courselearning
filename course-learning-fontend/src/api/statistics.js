import axios from "axios";
import { STATISTICS_MODULE } from "@/api/_prefix";

/**
 * 获取统计信息 GET
 * @returns
 */
export function getStatisticData(){
    return axios.get(`${STATISTICS_MODULE}/getInfo`).then(res=>{
        return res.data;
    })
}
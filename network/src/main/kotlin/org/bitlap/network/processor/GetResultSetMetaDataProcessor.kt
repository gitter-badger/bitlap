package org.bitlap.network.processor

import com.alipay.sofa.jraft.rpc.RpcContext
import com.alipay.sofa.jraft.rpc.RpcProcessor
import org.bitlap.common.exception.BitlapException
import org.bitlap.network.core.NetworkService
import org.bitlap.network.core.OperationHandle
import org.bitlap.network.proto.driver.BGetResultSetMetadata

/**
 * GetResultSetMetadata
 *
 * @author 梦境迷离
 * @since 2021/6/5
 * @version 1.0
 */
class GetResultSetMetaDataProcessor(private val networkService: NetworkService) :
    RpcProcessor<BGetResultSetMetadata.BGetResultSetMetadataReq>,
    ProcessorHelper {
    override fun handleRequest(rpcCtx: RpcContext, request: BGetResultSetMetadata.BGetResultSetMetadataReq) {
        val operationHandle = request.operationHandle
        val resp: BGetResultSetMetadata.BGetResultSetMetadataResp = try {
            val result = networkService.getResultSetMetadata(OperationHandle(operationHandle))
            BGetResultSetMetadata.BGetResultSetMetadataResp.newBuilder()
                .setStatus(success()).setSchema(result.toBTableSchema()).build()
        } catch (e: BitlapException) {
            e.printStackTrace()
            BGetResultSetMetadata.BGetResultSetMetadataResp.newBuilder().setStatus(error()).build()
        }
        rpcCtx.sendResponse(resp)
    }

    override fun interest(): String = BGetResultSetMetadata.BGetResultSetMetadataReq::class.java.name
}
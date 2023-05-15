package org.example.config;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



/**
 * 华为obs配置类
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "obs.config-storage")
public class ObsFileConfig {
  /** 节点地址 */
  private String endpoint;
  /** 自定义域名 */
  private String domain;
  /** 存储 Bucket */
  private String bucket;

  /** 访问 Key */
  private String accessKey;
  /** 访问 Secret */
  private String accessSecret;

  /**
   * 创建实例
   * @return
   */
  public ObsClient getInstance(){
    return new ObsClient(accessKey,accessSecret,endpoint);
  }

  /**
   * 销毁实列
   * @param obsClient
   */
  public void destroy(ObsClient obsClient)  {
    try{
      obsClient.close();
    }catch (ObsException e){
//      log.error("obs执行失败",e);
    }catch (Exception e){
//      log.error("执行失败",e);
    }
  }

}

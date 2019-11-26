package cn.ply.cloud.algorithmsvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: ply
 * @Description:
 * @Date: created in 2019/4/15
 * @Modified By:
 */
@SpringBootApplication
@EnableEurekaClient
public class AlgorithmServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgorithmServerApplication.class, args);
	}

}

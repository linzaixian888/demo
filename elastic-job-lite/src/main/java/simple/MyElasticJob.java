package simple;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author linzaixian
 * @since 2017-08-18 23:02:06 
 */
public class MyElasticJob implements SimpleJob{

    @Override
    public void execute(ShardingContext arg0) {
        System.out.println(arg0);
        System.out.println(arg0.getShardingItem());
        
    }

}

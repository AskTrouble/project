package com.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ConnectionZookeeper {
	
	public class WathcerDmc implements Watcher{

		public ZooKeeper zk ;
		
		public WathcerDmc(ZooKeeper zk){
			this.zk = zk;
		}
		
		@Override
		public void process(WatchedEvent event) {

			System.out.println("监控通知:" +  event.getPath());
			System.out.println("监控通知:" +  event.getState().name());
			System.out.println("监控通知:" +  event.getType());
			
			Watcher watcher = new WathcerDmc(zk);

			try {
				System.out.println("修改节点" + event.getPath() + "，数据为：" + new String(zk.getData(event.getPath(), watcher,null)));
			} catch (KeeperException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ConnectionZookeeper zz = new ConnectionZookeeper();
		
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 100, null);
		String path = "/dmc";
		
		zk.create(path, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		Watcher watcher = zz.new WathcerDmc(zk);
		
		System.out.println("创建节点" + path + "，数据为：" + new String(zk.getData(path, watcher, null)));
		
		Thread.sleep(100000000);
		
		zk.setData(path, "2".getBytes(), -1);
		System.out.println("修改节点"+ path + "，数据为："+ new String(zk.getData(path, null, null)));
		
		System.out.println(zk.exists(path, null));
		
		zk.delete(path, -1);
		
		System.out.println(zk.exists(path, null));
	}

}

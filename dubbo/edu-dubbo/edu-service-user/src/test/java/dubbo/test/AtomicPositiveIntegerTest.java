package dubbo.test;

import com.alibaba.dubbo.common.utils.AtomicPositiveInteger;

public class AtomicPositiveIntegerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		AtomicPositiveInteger tt = new AtomicPositiveInteger();
		;
		
		System.out.println(tt.getAndIncrement());

		System.out.println(tt.getAndIncrement());
		
	}

}

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import ru.specialist.tree.TreeNode;

public class Program {
	
	static Random rnd = new Random();
	static int total; 
	
	// n = level + 1
	public static void creatRandomTree(TreeNode node, int level)
	{
		node.left  = new TreeNode();
		node.right = new TreeNode();
		node.weight = rnd.nextInt(100);
		total += node.weight;
		level--;
		if (level == 0) 
		{
			node.left.weight = rnd.nextInt(100);
			node.right.weight = rnd.nextInt(100);
			total += node.left.weight;
			total += node.right.weight;
			return;
		}
		creatRandomTree(node.left, level);
		creatRandomTree(node.right, level);
	}
	
	
	public static int weightTree(TreeNode root)
	{
		return 
			root.weight+
			(root.left != null ? weightTree(root.left) : 0)+
			(root.right!= null ? weightTree(root.right): 0);
			
	}
	
	
	
	
	
	public static int weightTreeMulti(TreeNode root) throws InterruptedException, ExecutionException
	{
		
		class WeightCounter extends RecursiveTask<Integer>
		{
			TreeNode r;
			int level;
			public WeightCounter(TreeNode n, int level)
			{
				this.r = n;
				this.level = level;
			}
			@Override
			protected Integer compute() {
				final int newLevel = level-1;
				if (level <=0 )return weightTree(r);
				
				int sum = r.weight;
				WeightCounter w1 = null,w2 = null;
				if(r.left!=null)
				{
					w1 = new WeightCounter(r.left, newLevel);
					w1.fork();
				}
				if(r.right!=null)
				{
					w2 = new WeightCounter(r.right, newLevel);
					w2.fork();
				}
				if (w1 != null)
					sum += w1.join();
				if (w2 != null)
					sum += w2.join();
				return sum;
			}
		}	
		
		//return (new ForkJoinPool().
		//	invoke(
		//		new WeightCounter(root, 
		//			Runtime.getRuntime().availableProcessors()
		//			)));
		return ForkJoinPool.commonPool().invoke(
			new WeightCounter(root, Runtime.getRuntime().availableProcessors()));
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		int treeLevel = 27; // 2^(n+1)-1
		if (args.length >= 1)
			treeLevel = Integer.parseInt(args[0]);
		
		System.out.printf("Starting tree creation with depth %d...\n", treeLevel+1);
		TreeNode root = new TreeNode();
		creatRandomTree(root, treeLevel);
		
		
		System.out.printf("Tree created with total weight: %d\n",total);
		
		//weightTree(root);weightTree(root); // PROFILING
		
		long t1 = System.currentTimeMillis();
		int r1 = weightTree(root); 
		long t2 = System.currentTimeMillis();
		System.out.printf("Single weight: %d Time %d\n", r1, t2-t1);
		
		
		//weightTreeMulti(root);weightTreeMulti(root); // PROFILING
		
		long t3 = System.currentTimeMillis();
		int r2 = weightTreeMulti(root);
		long t4 = System.currentTimeMillis();
		System.out.printf("Multi weight: %d Time %d\n", r2, t4-t3);
		
		/*var task = new ForkJoinTask<Double>() {
			double r;
			@Override
			public Double getRawResult() {
				
				return r;
			}

			@Override
			protected void setRawResult(Double value) {
				r = value;
				
			}

			@Override
			protected boolean exec() {
				r = ...;
				return true;
			}
			
			
		};*/
		

	}

}

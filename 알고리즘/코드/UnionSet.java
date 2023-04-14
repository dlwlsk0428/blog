import java.util.Arrays;

public class UnionSet {
	public static void main(String[] args) {
		int[] parent = MakeSet(5);
		// 각 인덱스(=노드)는 자기 자신을 가리키고 있다
		System.out.println(Arrays.toString(parent));

		union(parent, 1, 2);
		System.out.println(Arrays.toString(parent));
		union(parent, 2, 3);
		System.out.println(Arrays.toString(parent));
		union(parent, 4, 5);
		System.out.println(Arrays.toString(parent));
		union(parent, 3, 5);
		System.out.println(Arrays.toString(parent));
		System.out.println(find(parent, 1));
		System.out.println(find(parent, 2));
		System.out.println(find(parent, 3));
		System.out.println(find(parent, 4));
		System.out.println(find(parent, 5));
	}

	private static void union(int[] parent, int a, int b) {
		// 각 집합을 대표하는 부모가 다른 부모로 편입 되어야 한다. 원소가 편입되어서는 안된다.
		a = find(parent, a);
		b = find(parent, b);
		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}

	private static int find(int[] parent, int x) {
		// 만일, 찾는 대상과 인덱스 번호가 같다면 그 인덱스(=노드)가 해당 집합의 부모이다.
		if (parent[x] == x)
			return x;
		// 그렇지 않다면, 해당 인덱스가 가리키는 값(부모 노드)을 따라 최종 부모노드까지 탐색한다.
		else
			return find(parent, parent[x]);
	}

	private static int[] MakeSet(int size) {
		// 각 인덱스에 번호가 대응하도록 사이즈를 1더하여 배열 선언.
		int[] arr = new int[size + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		return arr;
	}
}

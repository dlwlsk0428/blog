# DFS

<br>

그래프 알고리즘으로, 문제를 풀 때 상당히 많이 사용한다.

경로를 찾는 문제 시, 상황에 맞게 DFS와 BFS를 활용하게 된다.

<br>

### DFS

> 루트 노드 혹은 임의 노드에서 **다음 브랜치로 넘어가기 전에, 해당 브랜치를 모두 탐색**하는 방법

**스택 or 재귀함수**를 통해 구현한다.

<br>

- 모든 경로를 방문해야 할 경우 사용에 적합

<img src="https://upload.wikimedia.org/wikipedia/commons/7/7f/Depth-First-Search.gif" width="300">

##### 시간 복잡도

- 인접 행렬 : O(V^2)
- 인접 리스트 : O(V+E)

> V는 접점, E는 간선을 뜻한다

<br>

##### Code

```c
#include <stdio.h>

int map[1001][1001], dfs[1001];

void init(int *, int size);

void DFS(int v, int N) {

	dfs[v] = 1;
	printf("%d ", v);

	for (int i = 1; i <= N; i++) {
		if (map[v][i] == 1 && dfs[i] == 0) {
			DFS(i, N);
		}
	}

}

int main(void) {

	init(map, sizeof(map) / 4);
	init(dfs, sizeof(dfs) / 4);

	int N, M, V;
	scanf("%d%d%d", &N, &M, &V);

	for (int i = 0; i < M; i++)
	{
		int start, end;
		scanf("%d%d", &start, &end);
		map[start][end] = 1;
		map[end][start] = 1;
	}

	DFS(V, N);

	return 0;
}

void init(int *arr, int size) {
	for (int i = 0; i < size; i++)
	{
		arr[i] = 0;
	}
}
```

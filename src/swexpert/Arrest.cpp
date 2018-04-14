#include <iostream>
#include <vector>

using namespace std;

int N, M, R, C, L;
int map[51][51];
bool visit[51][51];
int cnt;

int d[4][2] = { { -1, 0 },{ 1, 0 },{ 0, -1 },{ 0, 1 } };
int r[8] = { 0, 15, 12, 3, 9, 5, 6, 10 };
vector<pair<int, int> > tmp;

void start(int x, int y, int d);

int main()
{
	int testcase, t;
	cin >> testcase;
	for (t = 0; t < testcase; t++) {
		cnt = 1;

		cin >> N >> M >> R >> C >> L;
		int i, j;
		for (i = 0; i < N; i++) {
			for (j = 0; j < M; j++) {
				visit[i][j] = false;
				cin >> map[i][j];
			}
		}

		vector<pair<int, int> > arr;
		arr.reserve(1 << N*M);
		arr.push_back(make_pair(R, C));
		tmp.clear();
		visit[R][C] = true;

		int arrSize = 0;
		int value;
		while (L > 1) {
			L--;
			if (arr.size() == 0) {
				arr = tmp;
				tmp.clear();
			}

			while (arrSize = arr.size()) {
				int x = arr[arrSize - 1].first;
				int y = arr[arrSize - 1].second;
				arr.pop_back();

				value = r[map[x][y]];
				if (value & (1 << 3))
					start(x + d[0][0], y + d[0][1], 0);
				if (value & (1 << 2))
					start(x + d[1][0], y + d[1][1], 1);
				if (value & (1 << 1))
					start(x + d[2][0], y + d[2][1], 2);
				if (value & (1 << 0))
					start(x + d[3][0], y + d[3][1], 3);
			}
		}
		cout << "#" << t + 1 << " " << cnt << endl;
	}
	return 0;
}

void start(int x, int y, int d) {
	if (x < 0 || x > N - 1 || y < 0 || y > M - 1)
		return;

	if (visit[x][y] == true)
		return;

	int value = r[map[x][y]];
	if (value == 0)
		return;

	switch (d) {
	case 0:
		if (!(value & 1 << 2))
			return;
		break;
	case 1:
		if (!(value & 1 << 3))
			return;
		break;
	case 2:
		if (!(value & 1 << 0))
			return;
		break;
	case 3:
		if (!(value & 1 << 1))
			return;
		break;
	}

	visit[x][y] = true;
	tmp.push_back(make_pair(x, y));
	cnt++;
}

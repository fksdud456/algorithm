	private static void moveRed(Ball tmp) {
		int tx = tmp.Rx + (dx[tmp.dir]);
		int ty = tmp.Ry + (dy[tmp.dir]);
		while (tx >= 0 && tx < N && ty >= 0 && ty < M) {
			if (map[tx][ty] == EMPTY) {
				tmp.Rx = tx;
				tmp.Ry = ty;

				tx = tmp.Rx + (dx[tmp.dir]);
				ty = tmp.Ry + (dy[tmp.dir]);
			} else if (map[tx][ty] == HOLE) {
				tmp.Rx = tx;
				tmp.Ry = ty;
				break;
			} else {
				break;
			}
		}

		tx = tmp.Bx + (dx[tmp.dir]);
		ty = tmp.By + (dy[tmp.dir]);

		while (tx >= 0 && tx < N && ty >= 0 && ty < M) {
			if (tx == tmp.Rx && ty == tmp.Ry) {
				if (map[tx][ty] == HOLE) {
					tmp.Bx = tx;
					tmp.By = ty;
				}
				break;
			}

			if (map[tx][ty] == EMPTY) {
				tmp.Bx = tx;
				tmp.By = ty;
				tx = tmp.Bx + (dx[tmp.dir]);
				ty = tmp.By + (dy[tmp.dir]);
			} else if (map[tx][ty] == HOLE) {
				tmp.Bx = tx;
				tmp.By = ty;
				break;
			} else {
				break;
			}
		}
	}

function rotate90(matrix) {
    /*
     * nmatrix.row = matrix.col
     * nmatrix.col = matrix.row
     *  _ _                   _ _
     * |1|2|      _ _ _      |6|5|      _ _ _
     * |3|4| ==> |5|3|1| ==> |4|3| ==> |2|4|6|
     * |5|6|     |6|4|2|     |2|1|     |1|3|5|
     *
     * by observation
     * nx = y
     * ny = r - x - 1
     */
    const rl = matrix.length;
    const cl = matrix[0].length;

    const nmatrix = Array(cl);
    for (let row = 0; row < cl; row++) {
        nmatrix[row] = Array(rl).fill(0);
    }

    for (let x = 0; x < rl; x++) {
        for (let y = 0; y < cl; y++) {
            nmatrix[y][rl - x - 1] = matrix[x][y];
        }
    }

    return nmatrix;
}

let matrix1 = [
    [1, 1, 1],
    [0, 1, 0],
    [0, 1, 0]
]

let matrix2 = [
    [1, 1],
    [0, 1],
    [0, 1]
]

let matrix3 = [
    [1, 1],
    [1, 1]
]

let matrix4 = [
    [1, 1, 1, 1]
]

function pp(m, msg) {
    console.log(msg);
    for (let r of m) {
        console.log(r);
    }
}

pp(matrix1, '\nbefore rotation 90 degree');
matrix1 = rotate90(matrix1);
pp(matrix1, 'after rotation 90 degree');
matrix1 = rotate90(matrix1);
pp(matrix1, 'after rotation 90 degree');
matrix1 = rotate90(matrix1);
pp(matrix1, 'after rotation 90 degree');
matrix1 = rotate90(matrix1);
pp(matrix1, 'after rotation 90 degree');

pp(matrix2, '\nbefore rotation 90 degree');
matrix2 = rotate90(matrix2);
pp(matrix2, 'after rotation 90 degree');
matrix2 = rotate90(matrix2);
pp(matrix2, 'after rotation 90 degree');
matrix2 = rotate90(matrix2);
pp(matrix2, 'after rotation 90 degree');
matrix2 = rotate90(matrix2);
pp(matrix2, 'after rotation 90 degree');

pp(matrix3, '\nbefore rotation 90 degree');
matrix3 = rotate90(matrix3);
pp(matrix3, 'after rotation 90 degree');
matrix3 = rotate90(matrix3);
pp(matrix3, 'after rotation 90 degree');
matrix3 = rotate90(matrix3);
pp(matrix3, 'after rotation 90 degree');
matrix3 = rotate90(matrix3);
pp(matrix3, 'after rotation 90 degree');

pp(matrix4, '\nbefore rotation 90 degree');
matrix4 = rotate90(matrix4);
pp(matrix4, 'after rotation 90 degree');
matrix4 = rotate90(matrix4);
pp(matrix4, 'after rotation 90 degree');
matrix4 = rotate90(matrix4);
pp(matrix4, 'after rotation 90 degree');
matrix4 = rotate90(matrix4);
pp(matrix4, 'after rotation 90 degree');

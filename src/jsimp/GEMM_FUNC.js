function multiply(m, n, k) {
    var a = new Array();
    var c = new Array();
    var b = new Array();
    for (var i = 0; i<m;i++) {
        a[i] = new Array();
        for (var p = 0;p<k;p++) {
            a[i][p] = Math.random() * 10;
        }
    }
    for (var p = 0; p<k;p++) {
        b[p] = new Array();
        for (var j = 0;j<n;j++) {
            b[p][j] = Math.random() * 10;
        }
    }

    for (var i = 0; i < m; i++) {
        c[i] = new Array();
        for (var p = 0; p < k; p++) {
            for (var j = 0; j < n; j++) {
                c[i][j] += a[i][p] * b[p][j];
            }
        }
    }

    return c;
}
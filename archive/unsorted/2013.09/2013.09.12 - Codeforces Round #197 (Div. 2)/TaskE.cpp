#include <cstdio>
#include <iostream>
#include <vector>
#include <algorithm>
#include <cassert>
#include <cmath>
using namespace std;

int n;
vector <int> v;
vector <int> ansl, ansr;

bool rec(int k) {
    bool ok = true;

    for (int i = 0; i < n - 1; i++) {
        if (v[i] + 1 != v[i + 1]) {
            ok = false;
            break;
        }
    }

    if (ok) {
        ansl.resize(k - 1);
        ansr.resize(k - 1);
        return true;
    }

    if (k == 4)
        return false;

    vector <int> badPlaces;

    badPlaces.push_back(0);
    for (int i = 1; i < n - 1; i++) {
        if (abs(v[i + 1] - v[i]) != 1 || v[i + 1] - v[i] != v[i] - v[i - 1]) {
            badPlaces.push_back(i);
        }
    }
    badPlaces.push_back(n - 1);

    for (int lpos = (int)badPlaces.size() - 1; lpos >= 0; lpos--) {
        for (int rpos = lpos + 1; rpos < (int)badPlaces.size(); rpos++) {
            int l = badPlaces[lpos];
            int r = badPlaces[rpos];

            for (int i = l, j = r; i < j; i++, j--) {
                swap(v[i], v[j]);
            }

            if (rec(k + 1)) {
                ansl[(int)ansl.size() - k] = l + 1;
                ansr[(int)ansr.size() - k] = r + 1;
                //ansl[k - 1] = l + 1;
                //ansr[k - 1] = r + 1;
                return true;
            }

            for (int i = l, j = r; i < j; i++, j--) {
                swap(v[i], v[j]);
            }
        }
    }

    return false;
}

int main() {
    ios_base::sync_with_stdio(false);

    cin >> n;

    v.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }

    assert(rec(1));

    cout << ansl.size() << endl;
    for (int i = 0; i < (int)ansl.size(); i++) {
        cout << ansl[i] << " " << ansr[i] << endl;
    }
}
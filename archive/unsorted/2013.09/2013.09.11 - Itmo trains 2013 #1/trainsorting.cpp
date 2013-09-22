#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = (int)1e9;

#define TASK "trainsorting"

ifstream cin(TASK".in");
ofstream cout(TASK".out");

int solve(vector <int> &a) {
    int n = (int)a.size();

    vector <int> d(n + 1);

    d[0] = -INF;
    for (int i = 1; i <= n; i++) {
	    d[i] = INF;
	}

    for (int i = 0; i < n; i++) {
	    int j = (int)(upper_bound(d.begin(), d.end(),  a[i]) - d.begin());
	    if (d[j - 1] < a[i] && a[i] < d[j]) {
		    d[j] = a[i];
		}
    }

    for (int i = n; i >= 0; i--)
        if (d[i] != INF)
            return i;

    return 0;
}

int main() {
	int n;
	cin >> n;

	vector <int> v(n);
	for (int i = 0; i < n; i++) {
	    cin >> v[i];
	}

	int ans = 0;

	for (int start = 0; start < n; start++) {
	    vector <int> a;
	    a.reserve(n - start);
	    for (int i = start + 1; i < n; i++) {
	        if (v[i] > v[start])
	            a.push_back(v[i]);
	    }

	    int x = solve(a);

	    a.clear();
	    for (int i = start + 1; i < n; i++) {
            if (v[i] < v[start])
        	    a.push_back(-v[i]);
        }

        ans = max(ans, x + solve(a) + 1);
	}

	cout << ans << endl;
}


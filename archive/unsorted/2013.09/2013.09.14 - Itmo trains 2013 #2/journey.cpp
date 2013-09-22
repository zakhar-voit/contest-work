#include <fstream>
#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int n;
int k;
int m;
int mx = 0;
vector <char> vis;
vector <int> parent;
vector <int> parentPos;
vector <int> isGood;
vector <vector<int> > g, w;


vector <int> lists;
void calcLists(int v, int p, int depth = 0) {
        vis[v] = true;
        parent[v] = p;

	if (isGood[v]) {
		mx = max(mx, depth);
	}

        if ((int)g[v].size() == 1) {
            lists.push_back(v);
        }
        for (int i = 0; i < (int)g[v].size(); i++) {
            int to = g[v][i];

            if (!vis[to]) {
                parentPos[to] = i;
                calcLists(to, v, depth + w[v][i]);
            }
        }
    }

#define TASK "journey"

void solve() {
	ifstream cin(TASK".in");
	ofstream cout(TASK".out");

        int n;
	cin >> n;
        int k;
	cin >> k;
	--k;

        g.resize(n);
        w.resize(n);

        vis.resize(n);
        parent.assign(n, -1);
        parentPos.resize(n);

        for (int i = 0; i < n - 1; i++) {
            int from, to, dist;
	    cin >> from >> to >> dist;
		--from; --to;
            g[from].push_back(to);
            g[to].push_back(from);
            w[from].push_back(dist);
            w[to].push_back(dist);
        }

        cin >> m;

        vector <int> cities(m);
        isGood.resize(n);
        for (int i = 0; i < m; i++) {
            cin >> cities[i];
	--cities[i];
            isGood[cities[i]] = true;
        }

        vis[k] = true;

        calcLists(k, -1);

        vis.assign(n, false);
        vis[k] = true;

        int ans = 0;
        for (int i = 0; i < (int)cities.size(); i++) {
	    int city = cities[i];
            int dist = 0;
            for (int cur = city;; cur = parent[cur]) {
                if (vis[cur]) {
                    ans += dist * 2;
                    break;
                }

                vis[cur] = true;

                if (dist != -1)
                    dist += w[parent[cur]][parentPos[cur]];
            }
        }

        cout << ans - mx << endl;
    }
    
int main() {
	solve();
}

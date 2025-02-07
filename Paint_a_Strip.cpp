#include <bits/stdc++.h>
using namespace std;

//Speed
#define fastio() ios_base::sync_with_stdio(false), cin.tie(nullptr), cout.tie(nullptr);

//Macros
#define IOtext freopen("input.txt", "r", stdin); freopen("output.txt", "w", stdout);
#define PI (3.141592653589)
#define M 1000000007
#define pb push_back
#define f first
#define s second
#define set_bits(x) __builtin_popcountll(x)
#define zero_bits(x) __builtin_ctzll(x)
#define sz(s) (int)(s.size())
#define digCnt(n) ((int)log10(n) + 1)
#define all(x) x.begin(), x.end()
#define rall(x) x.rbegin(), x.rend()
#define MAX(x) *max_element(all(x))
#define MIN(x) *min_element(all(x))
#define SUM(x) accumulate(all(x), 0LL)
#define B break
#define C continue
#define py cout << "YES" << endl
#define pn cout << "NO" << endl
#define pm cout << "-1" << endl
#define ps(x,y) fixed << setprecision(y) << x
#define endl '\n'

typedef long long ll;
typedef unsigned long long ull;
typedef long double lld;
typedef pair<int, int> pi;
typedef pair<ll, ll> pl;
typedef vector<int> vi;
typedef vector<ll> vl;
typedef vector<pi> vpi;
typedef vector<pl> vpl;
typedef vector<vi> vvi;
typedef vector<vl> vvl;
typedef map<int, int> mii;
typedef map<ll, ll> mll;
typedef set<int> seti;
typedef set<ll> setll;
typedef priority_queue<ll> pqll;
typedef priority_queue<ll, vl, greater<ll>> pqllmn;

#ifndef ONLINE_JUDGE
#define debug(x) cerr << #x << " "; _print(x); cerr << endl;
#else
#define debug(x)
#endif

void _print(ll t) { cerr << t; }
void _print(int t) { cerr << t; }
void _print(string t) { cerr << t; }
void _print(char t) { cerr << t; }
void _print(lld t) { cerr << t; }
void _print(double t) { cerr << t; }
void _print(ull t) { cerr << t; }

template <class T, class V> void _print(pair<T, V> p);
template <class T> void _print(vector<T> v);
template <class T> void _print(set<T> v);
template <class T, class V> void _print(map<T, V> v);
template <class T> void _print(multiset<T> v);
template <class T, class V> void _print(pair<T, V> p) {
    cerr << "{"; _print(p.first); cerr << ","; _print(p.second); cerr << "}";
}
template <class T> void _print(vector<T> v) {
    cerr << "[ "; for (T i : v) { _print(i); cerr << " "; } cerr << "]";
}
template <class T> void _print(set<T> v) {
    cerr << "[ "; for (T i : v) { _print(i); cerr << " "; } cerr << "]";
}
template <class T> void _print(multiset<T> v) {
    cerr << "[ "; for (T i : v) { _print(i); cerr << " "; } cerr << "]";
}
template <class T, class V> void _print(map<T, V> v) {
    cerr << "[ "; for (auto i : v) { _print(i); cerr << " "; } cerr << "]";
}

// Operator overloads
template<typename T> // cin >> vector<T>
istream& operator>>(istream &in, vector<T> &v) {
    for (auto &it : v) cin >> it; return in;
}
template<typename T> // cout << vector<T>
ostream& operator<<(ostream &out, const vector<T> &v) {
    for (auto &it : v) out << it << " "; return out;
}
template<typename T, typename V> // cout << map<T, V>
ostream& operator<<(ostream &out, const map<T, V> &c) {
    for (auto &it : c) out << it.first << " " << it.second << endl; return out;
}

void solve()
{
   ll n;
   cin>>n;
   int ops = 1;
   ll curLen = 1;
   while(curLen < n){
        ops++;
        curLen = 2*curLen + 2;
   }
   cout<<ops<<endl;
}

int32_t main()
{
    fastio()
    #ifndef ONLINE_JUDGE
        freopen("Error.txt", "w", stderr);
    #endif
    
    int t = 1;
    cin >> t;
    while(t--)
    {
        solve();
    }
    return 0;
}

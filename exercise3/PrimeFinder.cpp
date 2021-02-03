#include <iostream>
#include <condition_variable>
#include <thread>
#include <functional>
#include <list>

using namespace std;

class PrimeFinder{

    int current;
    mutex curret_mutex;
    int last;
    mutex primes_mutex;
    list<thread> thread_list;
    list<int> primes; 

    public:
        PrimeFinder(int start, int end, int thread_amount){
            current = start;
            last = end;
            for(int i= 0; i<thread_amount; i++){
                thread_list.emplace_back([this]{
                while(current != last){
                    int test = 0;
                    curret_mutex.lock();
                    test = current;
                    current++;
                    curret_mutex.unlock();
                    testPrime(test);
                }
            });
            if(current == last)break;
        }
            
            for(auto &thread: thread_list){
                thread.join();
            }
        }
        void testPrime(int test){
            bool isPrime = true;
            for (int i = 2; i<test; i++){
                if((test%i) == 0){
                    isPrime = false;
                }
            }
            if(isPrime){
                primes_mutex.lock();
                primes.emplace_back(test);
                primes_mutex.unlock();
            }
        }
        ~PrimeFinder(){
            thread_list.clear();
            primes.sort();
            for(auto &prime : primes){
                printf("%d ", prime);
            }
            printf("\n");
        }
};
int main(){
    {
        PrimeFinder(3, 500, 100);
    }
}
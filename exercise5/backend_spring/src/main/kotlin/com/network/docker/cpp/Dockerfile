FROM gcc:4.9
RUN mkdir /cpp
WORKDIR /cpp
COPY main.cpp /cpp/
RUN g++ -o main main.cpp
CMD ["./main"]
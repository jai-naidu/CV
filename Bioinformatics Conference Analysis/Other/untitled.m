test1 = mvnrnd([0 0 0],(10^-1) * eye(3),100);
test2 = mvnrnd([1 1 1],(10^-1) * eye(3),100);
data_test = [test1;test2];

tsne(data_test);

figure(2)
plot(data_test(1:100, 1), data_test(1:100, 2), 'o');
hold on;
plot(data_test(101:200, 1), data_test(101:200, 2), 'o');
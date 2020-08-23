function Y = gassian4D(n)
    mu = [0 0 0 0];
    sigma = (10^-4) * eye(4);
    rng('default')
    Y = mvnrnd(mu,sigma,n);
end
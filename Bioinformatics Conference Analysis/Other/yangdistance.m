%calculates the yang distance between two vectors
function [d, dn] = yangdistance (x, y, p)

if isvector(x) == false || isvector(y) == false
    error('Inputs not vectors');
end

if size(x, 1) ~= size(y, 1) || size(x, 2) ~= size(y, 2)
    error('Dimensions do not agree');
end

if length(find(x < 0)) > 0 || length(find(y < 0)) > 0
    error('There are negatives and there should not be any');
end

if p <= 0
    error('Bad p');
end

ps = 0; ng = 0;
for i = 1 : length(x)
    if x(i) >= y(i)
        ps = ps + x(i) - y(i);
    else
        ng = ng + y(i) - x(i);
    end
end

d = (ps ^ p + ng ^ p) ^ (1/p);

z = abs(x - y);

denom = 0;
for i = 1 : length(x)
    denom = denom + max([abs(x(i)) abs(y(i)) abs(z(i))]);
end

dn = d / denom;

return
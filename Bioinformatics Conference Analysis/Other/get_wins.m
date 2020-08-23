%counts the number of wins of one conferences data against another
function [y, ncounter] = get_wins(i,j,data)
    array1 = data(:,i);
    array2 = data(:,j);
    a1counter = 0;
    a2counter = 0;
    ncounter = 0;
    tiecounter = 0;
    give = 0;
    
    for k = 1:length(data)
        if array1(k)==0 || array2(k)==0
            continue
        elseif array1(k) > array2(k)
            a1counter = a1counter + 1;
            ncounter = ncounter + 1;
        elseif array1(k) < array2(k)
            a2counter = a2counter + 1;
            ncounter = ncounter + 1;
        elseif array1(k) == array2(k)
            ncounter = ncounter + 1;
            
            if tiecounter == 0
                if a1counter < a2counter
                    a1counter = a1counter + 1;
                    give = 1;
                elseif a2counter < a1counter
                    a2counter = a2counter + 1;
                    give = 2;
                else
                    r = randi(2,1,1);
                    if r == 1
                        give = 1;
                        a1counter = a1counter + 1;
                    else
                        give = 2;
                        a2counter = a2counter + 1;
                    end
                end
            else
                if give == 1
                    give = 2;
                    a2counter = a2counter + 1;
                elseif give == 2
                    give = 1;
                    a1counter = a1counter + 1;
                end
            end
            
            tiecounter = tiecounter + 1;
        end
    end
    y = a1counter;
end
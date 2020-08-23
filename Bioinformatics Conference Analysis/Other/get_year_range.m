%outputs an array that consists of all years in which both specified
%conferences occured
function y = get_year_range(conf1,conf2)
    IS = [1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,...
        2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017];
    EC = [2002,2003,2005,2006,2008,2010,2012,2014,2016];
    PS = [1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,...
        2008,2009,2010,2011,2012,2013,2014,2015,2016,2017];
    BC = [2010,2011,2012,2013,2014,2015,2016,2017];
    RE = [1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,...
        2009,2010,2011,2012,2013,2014,2015,2016,2017];
    
    if inarray("ISMB",conf1)
        temp1 = IS;
    elseif inarray("ISMB-ECCB",conf1)
        temp1 = IS;
    elseif inarray("ECCB",conf1)
        temp1 = EC;
    elseif inarray("PSB",conf1)
        temp1 = PS;
    elseif inarray("BCB",conf1)
        temp1 = BC;
    elseif inarray("RECOMB",conf1)
        temp1 = RE;
    end
    if inarray("ISMB",conf2)
        temp2 = IS;
    elseif inarray("ISMB-ECCB",conf2)
        temp2 = IS;
    elseif inarray("ECCB",conf2)
        temp2 = EC;
    elseif inarray("PSB",conf2)
        temp2 = PS;
    elseif inarray("BCB",conf2)
        temp2 = BC;
    elseif inarray("RECOMB",conf2)
        temp2 = RE;
    end
    y = intersect(temp1,temp2);
end
mkdir LAB6 2> NUL
wmic os get Caption > LAB6\wmicCaption.txt 
wmic os get FreePhysicalMemory,TotalVisibleMemorySize > LAB6\wmicMemory.txt 
wmic logicaldisk get name,description > LAB6\wmicDiscs.txt
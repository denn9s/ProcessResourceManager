# ProcessResourceManager

A process and resource manager.

Inputs (through .txt file, separated by lines)

1. cr \<name\> \<priority\>  
&nbsp;&nbsp;&nbsp;&nbsp; Create new process of <name> with priority being 1 or 2 (0 is init process)
2. de \<name\>  
&nbsp;&nbsp;&nbsp;&nbsp;Destroy process \<name\>
3. req \<name\> \<amount of units\>  
&nbsp;&nbsp;&nbsp;&nbsp;Requests <amount> from resources R1, R2, R3, or R4
4. rel \<name\> \<amount of units\>  
&nbsp;&nbsp;&nbsp;&nbsp;Releases <amount> from resources R1, R2, R3, or R4
5. to  
&nbsp;&nbsp;&nbsp;&nbsp;Timeout
